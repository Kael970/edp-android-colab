package com.example.myapplication
 
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
 
class DishViewModel : ViewModel() {
 
    private val _dishes = MutableStateFlow(
        listOf(
            Dish(id = 1, name = "Chicken Adobo"),
            Dish(id = 2, name = "Sinigang na Baboy")
        )
    )
    val dishes: StateFlow<List<Dish>> = _dishes.asStateFlow()
 
    private var nextId = 100
 
    // ---------------- DISH CRUD ----------------
 
    fun addDish(name: String) {
        if (name.isBlank()) return
        val newDish = Dish(id = nextId++, name = name.trim())
        _dishes.value = _dishes.value + newDish
    }
 
    fun getDish(dishId: Int): Dish? {
        return _dishes.value.find { it.id == dishId }
    }
 
    // TODO 1: Update dish name
    fun updateDish(dishId: Int, newName: String) {
        if (newName.isBlank()) return
        _dishes.update { list ->
            list.map { if (it.id == dishId) it.copy(name = newName.trim()) else it }
        }
    }
 
    // TODO 2: Delete dish
    fun deleteDish(dishId: Int) {
        _dishes.update { list ->
            list.filter { it.id != dishId }
        }
    }
 
    // ---------------- RECIPE CRUD ----------------
 
    // TODO 3: Add recipe step
    fun addRecipe(dishId: Int, text: String) {
        if (text.isBlank()) return
        _dishes.update { list ->
            list.map { dish ->
                if (dish.id == dishId) {
                    dish.copy(recipes = dish.recipes + Recipe(id = nextId++, text = text.trim()))
                } else dish
            }
        }
    }
 
    // TODO 4: Update recipe step text
    fun updateRecipe(dishId: Int, recipeId: Int, newText: String) {
        if (newText.isBlank()) return
        _dishes.update { list ->
            list.map { dish ->
                if (dish.id == dishId) {
                    dish.copy(recipes = dish.recipes.map { 
                        if (it.id == recipeId) it.copy(text = newText.trim()) else it 
                    })
                } else dish
            }
        }
    }
 
    // TODO 5: Delete recipe step
    fun deleteRecipe(dishId: Int, recipeId: Int) {
        _dishes.update { list ->
            list.map { dish ->
                if (dish.id == dishId) {
                    dish.copy(recipes = dish.recipes.filter { it.id != recipeId })
                } else dish
            }
        }
    }
}
