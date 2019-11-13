package fr.gtm.contacts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.gtm.contacts.dao.ContactDAO;
import fr.gtm.contacts.dto.ContactDTO;
import fr.gtm.contacts.entities.Adresse;
import fr.gtm.contacts.entities.Civilite;
import fr.gtm.contacts.entities.Contact;

@Path("/contacts")
public class ContactsService {
@EJB private ContactDAO dao;

@POST
@Path("/add")
@Consumes(MediaType.APPLICATION_JSON)
public ContactDTO create(Contact contact) {
	dao.create(contact);
	ContactDTO cdto = new ContactDTO(contact);
	return cdto;
	}

@DELETE
@Path("/del/{id}")
public void delete(@PathParam("id")long id) {
	dao.delete(dao.getContactById(id));
	}

@POST
@Path("/edit")
@Consumes(MediaType.APPLICATION_JSON)
public void update(Contact contact) {
	dao.update(contact);
	}

public List<Contact> getContactByCivilite(Civilite civilite){
	return dao.getContactByCivilite(civilite);
	}

@GET
@Path("/all")
@Produces(MediaType.APPLICATION_JSON)
public List<ContactDTO> getAllContact(){
	List<ContactDTO> dtos = new ArrayList<ContactDTO>();
	List<Contact> contacts = dao.getAllContact();
	for(Contact c : contacts) {
		dtos.add(new ContactDTO(c));
	}
	return dtos;
	}

@GET
@Path("/get/{id}")
@Produces(MediaType.APPLICATION_JSON)
public ContactDTO getContactById(@PathParam("id")long id) {
	ContactDTO cdto = new ContactDTO(dao.getContactById(id));
	return cdto;	
}

@GET
@Path("/adresses/{id}")
@Produces(MediaType.APPLICATION_JSON)
public List<Adresse> getAdresseByContactId(@PathParam("id")long id){
	return dao.getAdressesByContactId(id);
}

public List<Contact> getContactByNom(String nom){
	return dao.getContactByNom(nom);
	}

public List<Contact> getContactByAdresseNotNull(){
	return dao.getContactByAdresseNotNull();
	}


}
