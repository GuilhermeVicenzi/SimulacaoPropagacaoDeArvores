package simulacao.persistencia;

import org.hibernate.Session;
import org.hibernate.Transaction;
import simulacao.simulador.Simulador;

public class SimuladorRepository {

    public void salvar(Simulador simulador) {

        Session session =
                HibernateUtil.getSessionFactory()
                        .openSession();

        Transaction tx =
                session.beginTransaction();

        session.merge(simulador);

        tx.commit();
        session.close();
    }
}
