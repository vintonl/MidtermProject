package com.skilldistillery.jpanommpa.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.jpanommpa.entities.Category;
import com.skilldistillery.jpanommpa.entities.Recipe;
import com.skilldistillery.jpanommpa.entities.RecipeIngredient;
import com.skilldistillery.jpanommpa.entities.RecipeType;

@Transactional
@Service
public class RecipeDAOImpl implements RecipeDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Recipe createRecipe(Recipe r, RecipeIngredient[] ri) {
		r.setDateCreated(LocalDate.now());
		r.setCategory(em.find(Category.class, r.getCategory().getId()));
		r.setRecipeType(em.find(RecipeType.class, r.getRecipeType().getId()));
		em.persist(r);

		for (RecipeIngredient recipeIngredient : ri) {
			if (recipeIngredient != null) {
				recipeIngredient.setRecipe(r);
				em.persist(recipeIngredient);
			} else
				break;
		}

		em.flush();

		return r;
	}

	@Override
	public List<Recipe> selectAllRecipe() {
		String query = "Select r from Recipe r where active = :a";

		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("a", true).getResultList();

		return results;
	}

	@Override
	public List<Recipe> selectRecipeByName(String name) {
		String query = "Select r from Recipe r where r.name like :name and active = :a";

		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("a", true).setParameter("name", "%" + name + "%")
				.getResultList();

		return results;
	}

	@Override
	public List<Recipe> selectRecipeByCategory(String category) {
		String query = "Select r from Recipe r where r.category.name = :word  and active = :a";

		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("a", true).setParameter("word", category).getResultList();

		return results;
	}

	@Override
	public List<Recipe> selectRecipeByIngredient(String ingredient) {
		String query = "Select r from Recipe r where r.recipeIngredients.ingredient.name like :name and active = :a";

		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("a", true).setParameter("name", "%" + ingredient + "%")
				.getResultList();

		return results;
	}

	@Override
	public List<Recipe> selectRecipeByType(String type) {
		String query = "Select r from Recipe r where r.recipeType.name = :word and active = :a";

		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("a", true).setParameter("word", type).getResultList();

		return results;
	}

	@Override
	public List<Recipe> selectRecipeByCookbook(String cookbook) {
		String query = "Select r from Recipe r where r.cookbook like :word and active = :a";

		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("a", true).setParameter("word", "%" + cookbook + "%")
				.getResultList();

		return results;
	}

	@Override
	public Recipe updateRecipe(Recipe r, RecipeIngredient[] ri) {
		// get a match from the database
		Recipe matchingRecipe = em.find(Recipe.class, r.getId());
		// update to match user form input
		matchingRecipe.setName(r.getName());
		matchingRecipe.setDateCreated(LocalDate.now());
		matchingRecipe.setActive(true);
		matchingRecipe.setRecipeIngredients(r.getRecipeIngredients()); // ?
//		matchingRecipe.setCategory(r.getCategory());
//		matchingRecipe.setRecipeType(r.getRecipeType());
		matchingRecipe.setCategory(em.find(Category.class, r.getCategory().getId()));
		matchingRecipe.setRecipeType(em.find(RecipeType.class, r.getRecipeType().getId()));
		matchingRecipe.setPrepTime(r.getPrepTime());
		matchingRecipe.setInstructions(r.getInstructions());
		matchingRecipe.setPhotoLink(r.getPhotoLink());
		matchingRecipe.setCookbook(r.getCookbook());
		matchingRecipe.setCookbookPageNumber(r.getCookbookPageNumber());
		matchingRecipe.setWebLink(r.getWebLink());
		matchingRecipe.setIsPublic(r.getIsPublic());
		em.persist(matchingRecipe);

		em.flush();
		
		//remove ALL existing recipeingredients before adding newly selected ones
		String query = "Select ri from RecipeIngredient ri where ri.recipe.id = :id";

		List<RecipeIngredient> results = em.createQuery(query, RecipeIngredient.class).setParameter("id", r.getId()).getResultList();
		for (RecipeIngredient recipeIngredient : results) {
			em.remove(recipeIngredient);
		}
		
		em.flush();
		
		
		for (RecipeIngredient recipeIngredient : ri) {
			if (recipeIngredient != null) {
				recipeIngredient.setRecipe(r);
				em.persist(recipeIngredient);
			} else
				break;
		}

		em.flush();

		return matchingRecipe;

	}

	@Override
	public boolean deleteRecipe(Recipe r) {
		// get a match from the database
		Recipe matchingRecipe = em.find(Recipe.class, r.getId());

		matchingRecipe.setActive(false);

		em.persist(matchingRecipe);

		em.flush();

		return true;
	}

//	@Override
//	public boolean isRecipePublic(int id) {
//		String query = "Select r from Recipe r where r.isPublic = :public and r.id = :id";
//
//		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("public", true).setParameter("id", id)
//				.getResultList();
//
//		if (results == null) {
//			return false;
//		} else {
//			return true;
//		}
//
//	}
//
//	@Override
//	public List<Recipe> selectAllPublicRecipe() {
//		String query = "Select r from Recipe r where r.isPublic = :public";
//
//		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("public", true).getResultList();
//
//		return results;
//	}
//
//	@Override
//	public List<Recipe> selectPublicRecipeByName(String name) {
//		String query = "Select r from Recipe r where r.name like :name and r.isPublic = :public";
//
//		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("name", "%" + name + "%")
//				.setParameter("public", true).getResultList();
//
//		return results;
//	}
//
//	@Override
//	public List<Recipe> selectPublicRecipeByCategory(String category) {
//		String query = "Select r from Recipe r where r.isPublic = :public and r.category.name like :word";
//
//		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("word", "%" + category + "%")
//				.setParameter("public", true).getResultList();
//
//		return results;
//	}
//
//	@Override
//	public List<Recipe> selectPublicRecipeByIngredient(String ingredient) {
//
//		String query = "Select r from Recipe r where r.isPublic = :public and r.recipeIngredients.ingredient.name in (:name)";
//
//		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("public", true)
//				.setParameter("name", ingredient).getResultList();
//
//		return results;
//	}
//
//	@Override
//	public List<Recipe> selectPublicRecipeByType(String type) {
//		String query = "Select r from Recipe r where r.isPublic = :public and r.recipeType.name like :word";
//
//		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("public", true)
//				.setParameter("word", "%" + type + "%").getResultList();
//
//		return results;
//	}
//
//	@Override
//	public List<Recipe> selectPublicRecipeByCookbook(String cookbook) {
//		String query = "Select r from Recipe r where r.isPublic = :public and r.cookbook like :word";
//
//		List<Recipe> results = em.createQuery(query, Recipe.class).setParameter("public", true)
//				.setParameter("word", "%" + cookbook + "%").getResultList();
//
//		return results;
//	}

	@Override
	public Recipe selectRecipeById(int id) {
		return em.find(Recipe.class, id);
	}

}
