package sr.unasat.dao;

import sr.unasat.entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AddressDAO {

    private EntityManager entityManager;

    public AddressDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void createAddress(Address address){
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();

    }

    public Address selectAddressByStreetname(String streetname) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Address a where a.streetname = :streetname";
        TypedQuery<Address> query = entityManager.createQuery(jpql, Address.class);
        query.setParameter("streetname", streetname);
        Address address = query.getSingleResult();
        entityManager.getTransaction().commit();
        return address;
    }

    public List<Address> selectAllAddress(){
        entityManager.getTransaction().begin();
        //hier begint transactie
        String jpql = "select a from Address a";
        TypedQuery<Address> query = entityManager.createQuery(jpql, Address.class);
        List<Address> addressList = query.getResultList();
        entityManager.getTransaction().commit();
        return addressList;
    }

    public void updateAddress(Address address){
        entityManager.getTransaction().begin();
        entityManager.merge(address);
        entityManager.getTransaction().commit();

    }

    public void deleteAddressByStreetname(Address address){
            entityManager.getTransaction().begin();
            entityManager.remove(address);
            entityManager.getTransaction().commit();
    }
}
