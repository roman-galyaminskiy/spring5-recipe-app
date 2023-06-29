package guru.springframework.bootstrap;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Note;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> { // implements CommandLineRunner
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public Bootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            recipeRepository.save(getGuacoRecipe());
        } catch (Exception e) {
            System.out.println("Bootstap failed");
            e.printStackTrace();
            System.exit(1);
        }
        log.info("Bootstrap finished!");
    }

    private Recipe getGuacoRecipe() throws IOException {
        Recipe guacoRecipe = Recipe.builder()
                .description("How to Make the Best Guacamole")
        .cookTime(10)
        .prepTime(10)
        .servings(2)
        .source("Simply Recipes")
        .categories(new HashSet<>())
        .difficulty(Difficulty.EASY)
        .directions("""
                Cut the avocados:

                Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.
                How to make guacamole - scoring avocado
                Elise Bauer
                Mash the avocado flesh:

                Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)
                How to make guacamole - smashing avocado with fork
                Elise Bauer
                Add the remaining ingredients to taste:

                Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.

                Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.

                Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.
                Serve immediately:

                If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)

                Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.

                Refrigerate leftover guacamole up to 3 days.\s
                """)
        .ingredients(new HashSet<>())
        .image(loadImage(new URL("https://www.simplyrecipes.com/thmb/njUiutoyF5mHaiOIqnciEGjVFyk=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2018__07__Guacamole-LEAD-1-47de4e6e47464daba88a7f42155a2af3.jpg")))
        .url("https://www.simplyrecipes.com/recipes/perfect_guacamole/")
        .build();

        // categories
        categoryRepository.findByDescription("Mexican").map(guacoRecipe.getCategories()::add).orElseThrow(() -> new IllegalArgumentException("Mexican category not found"));
        categoryRepository.findByDescription("Fast Food").map(guacoRecipe.getCategories()::add).orElseThrow(() -> new IllegalArgumentException("Fast Food category not found"));

        // ingredients
        guacoRecipe.addIngredient(Ingredient.builder()
                .amount(BigDecimal.valueOf(2))
                .unitOfMeasure(unitOfMeasureRepository.findByDescription("Piece").orElseThrow(() -> new IllegalArgumentException("Piece unit of measure not found")))
                .description("ripe avocados")
                .build());
        guacoRecipe.addIngredient(Ingredient.builder()
                .amount(BigDecimal.valueOf(0.25))
                .unitOfMeasure(unitOfMeasureRepository.findByDescription("Teaspoon").orElseThrow(() -> new IllegalArgumentException("Teaspoon unit of measure not found")))
                .description("kosher salt, plus more to taste")
                .build());

        // note
        guacoRecipe.setNote(Note.builder()
                .recipe(guacoRecipe)
                .recipeNotes("""
                Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving."""
                ).build());

        return guacoRecipe;
    }

    private static byte[] loadImage(URL url) throws IOException {
        try (InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int n;
            while (-1 != (n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }

            return out.toByteArray();
        }
    }

    // @Override
    // public void run(String... args) throws Exception {
    //     load();
    // }
}
