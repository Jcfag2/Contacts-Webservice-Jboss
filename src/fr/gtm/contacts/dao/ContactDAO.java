package fr.gtm.contacts.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.gtm.contacts.entities.Civilite;
import fr.gtm.contacts.entities.Contact;

@Singleton
public class ContactDAO {
	@PersistenceContext(name="contacts") private EntityManager em; 


	public void create(Contact contact) {
	em.persist(contact);
	}
	
	public Contact getContactById(long id) {
		Contact contact = em.find(Contact.class, id);
		return contact;
	}
	
	public void delete(Contact contact) {
		Contact c1 = em.find(Contact.class, contact.getId());
		em.remove(c1);

	}
	
	public void update(Contact contact) {
		em.merge(contact);
	}
	
	public List<Contact> getContactByCivilite(Civilite civilite){
		String sql = "SELECT c FROM Contact c WHERE c.civilite= :civilite";
		List<Contact> contacts = em.createQuery(sql, Contact.class).setParameter("civilite", civilite).getResultList();
		return contacts;
	}
	
	
	public List<Contact> getAllContact(){
		String sql = "SELECT c FROM Contact c";
		List<Contact> contacts = em.createQuery(sql, Contact.class).getResultList();
		return contacts;
	}
	
	public List<Contact> getContactByNom(String nom){
		List<Contact> contacts = em.createNamedQuery("Contact.getByNom", Contact.class).setParameter("nom", nom+"%").getResultList();
		return contacts;
	}
	
	public List<Contact> getContactByAdresseNotNull(){
		List<Contact> contacts = em.createNamedQuery("Contact.getAdresseNotEmpty", Contact.class).getResultList();
		return contacts;
	}

	
}
