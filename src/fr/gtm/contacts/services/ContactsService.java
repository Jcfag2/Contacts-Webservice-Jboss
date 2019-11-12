package fr.gtm.contacts.services;

import java.util.List;

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
import fr.gtm.contacts.entities.Civilite;
import fr.gtm.contacts.entities.Contact;

@Path("/contacts")
public class ContactsService {
@EJB private ContactDAO dao;

@POST
@Path("/add")
@Consumes(MediaType.APPLICATION_JSON)
public void create(Contact contact) {
	dao.create(contact);
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
public List<Contact> getAllContact(){
	return dao.getAllContact();
	}

public List<Contact> getContactByNom(String nom){
	return dao.getContactByNom(nom);
	}

public List<Contact> getContactByAdresseNotNull(){
	return dao.getContactByAdresseNotNull();
	}
}
