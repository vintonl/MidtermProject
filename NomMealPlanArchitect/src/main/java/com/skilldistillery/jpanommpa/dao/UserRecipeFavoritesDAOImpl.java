package com.skilldistillery.jpanommpa.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.jpanommpa.entities.Recipe;
import com.skilldistillery.jpanommpa.entities.User;
import com.skilldistillery.jpanommpa.entities.UserRecipe;
@Transactional
@Service
public class UserRecipeFavoritesDAOImpl implements UserRecipeFavoritesDAO{
	@PersistenceContext
	EntityManager em;

	@Override
	public UserRecipe createUserRecipe(UserRecipe ur) {
		
		em.persist(ur);
		
		em.flush();
		
		return ur;
	}

	@Override
	public UserRecipe updateUserRecipe(UserRecipe ur) {
		UserRecipe matchingUR = em.find(UserRecipe.class, ur.getId());

		matchingUR.setUser(ur.getUser());
		matchingUR.setRecipe(ur.getRecipe());
		matchingUR.setUserFavorite(true);
		matchingUR.setComment(ur.getComment());
		matchingUR.setDateLastMade(ur.getDateLastMade());
		

		em.persist(matchingUR);

		em.flush();

		return matchingUR;
	}

	@Override
	public void deleteUserRecipe(int id) {
		UserRecipe matchingUR = em.find(UserRecipe.class, id);
		
		em.remove(matchingUR);;
		
		em.flush();
		
	}

	@Override
	public List<UserRecipe> selectAllUserRecipe(int id) {
		String query = "Select u from UserRecipe u where u.user.id = :id";

		List<UserRecipe> results = em.createQuery(query, UserRecipe.class).setParameter("id", id).getResultList();

		return results;
	}

	@Override
	public List<UserRecipe> selectUserRecipeByRecipeName(String name) {
		String query = "Select u from UserRecipe u where u.recipe.name like :name";

		List<UserRecipe> results = em.createQuery(query, UserRecipe.class).setParameter("name", "%" + name + "%").getResultList();

		return results;
	}

	@Override
	public List<UserRecipe> selectUserRecipeByDateLastMade(LocalDate date) {
		String query = "Select u from UserRecipe u where u.dateLastMade = :date";

		List<UserRecipe> results = em.createQuery(query, UserRecipe.class).setParameter("date", date).getResultList();

		return results;
	}

}
