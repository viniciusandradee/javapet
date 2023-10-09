package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.animal.Animal;
import br.com.fiap.domain.entity.servico.Servico;
import br.com.fiap.domain.service.AnimalService;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class ServicoRepository implements Repository<Servico, Long> {

    private ConnectionFactory factory;

    private static final AtomicReference<ServicoRepository> instance = new AtomicReference<>();

    public ServicoRepository(){
        this.factory = ConnectionFactory.build();
    }

    public  static ServicoRepository build(){
        instance.compareAndSet(null, new ServicoRepository());
        return instance.get();
    }

    @Override
    public List<Servico> findAll() {
        List<Servico> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;
        try {
            String sql = "SELECT * FROM TB_SERVICO";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.isBeforeFirst()){
                while (rs.next()){
                    Long id = rs.getLong("ID_SERVICO");
                    String descricao = rs.getString("DS_SERVICO");
                    LocalDate realizao = rs.getDate("DT_REALIZACAO").toLocalDate();
                    String tipo = rs.getString("TP_SERVICO");
                    Long animal = rs.getLong("Animal");
                    AnimalService animalService = new AnimalService();
                    Animal animal = animalService.findById(animal);

                    list.add(new Servico(id, descricao, animal, realizao, tipo) {

                    });
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar os dados!\n" + e.getMessage());
        }finally {
            fecharObjetos(rs, st, con);
        }
        return list;
    }

    @Override
    public Servico findById(Long id) {
        Servico servico = null;
        var sql = "SELECT * FROM TB_SERVICE where ID_ANIMAL == ?";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.isBeforeFirst()){
                while (rs.next()){
                    String descricao = rs.getString("DS_SERVICE");
                    LocalDate realizao = rs.getDate("DT_REALIZAO").toLocalDate();
                    String tipo = rs.getString("TB_SERVICO");
                    Long animal = rs.getLong("ANIMAL");
                    AnimalService animalService = new AnimalService();
                    Animal animal = animalService.findById(animal);
                    servico = new Servico(id, descricao, animal, realizao, tipo);
                }
            }else {
                System.out.println("Dados não encontrados com o id: " + id);
            }
        }catch (SQLException e) {
            System.err.println("Não foi possível consultar os dados!\n" + e.getMessage());
        }finally {
            fecharObjetos(rs, ps, con);
        }
        return servico;
    }

    @Override
    public Servico persiste(Servico servico) {
        var sql = "BEGIN INSERT INTO TB_PF (DT_REALIZACAO, TP_SERVICO, DS_SERVICO) VALUES (?,?,?,?) returning ID_PESSOA into ?; END;";
        Connection con = factory.getConnection();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(sql);
            cs.setString(1, servico.getDescricao());
            cs.setDate(2,servico.getRealizacao());
            cs.setString(3, servico.getTipo());
            servico.getAnimal();
            cs.registerOutParameter(5, Types.BIGINT);
            cs.executeUpdate();
            servico.setId(cs.getLong(5));
        }catch (SQLException e) {
            System.err.println("Não foi possível inserir os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(null, cs, con);
        }
        return servico;
    }

    private static void fecharObjetos(ResultSet rs, Statement st, Connection con) {
        try {
            if (Objects.nonNull(rs) && !rs.isClosed()) {
                rs.close();
            }
            st.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Erro ao encerrar o ResultSet, a Connection e o Statment!\n" + e.getMessage());
        }
    }
}