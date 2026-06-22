package simulacao.persistencia;

import org.hibernate.Session;
import org.hibernate.Transaction;
import simulacao.simulador.Simulacao;

public class SimulacaoRepository {

    public void salvar(Simulacao simulacao) {
        Session session =
                HibernateUtil.getSessionFactory()
                        .openSession();

        Transaction tx =
                session.beginTransaction();

        session.persist(simulacao);

        tx.commit();
        session.close();
    }

    public Simulacao buscar(Long id) {
        Session session =
                HibernateUtil.getSessionFactory()
                        .openSession();

        Simulacao simulacao =
                session.find(Simulacao.class, id);

        session.close();

        return simulacao;
    }
}
