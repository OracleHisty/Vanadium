package com.oraclehisty.vanadium.fabric.datagen;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;


public class AnvilDatagen implements DataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    private final PackOutput.PathProvider AnvilRecipes;

    public AnvilDatagen(PackOutput output) {
        this.AnvilRecipes = output.createPathProvider(PackOutput.Target.DATA_PACK, "anvil_recipes");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Set<ResourceLocation> generatedDecayPatterns = Sets.newHashSet();
        List<CompletableFuture<?>> list = new ArrayList<>();


        BiConsumer<ResourceLocation, JsonObject> consumer = (resourceLocation, json)  -> {
            Path outputPath = AnvilRecipes.json(resourceLocation);
            list.add(DataProvider.saveStable(cache, json, outputPath));
        };

        generatePatterns(consumer);

        return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
    }

    private void generatePatterns(BiConsumer<ResourceLocation, JsonObject> consumer) {

    }


    @Override
    public String getName() {
        return "Anvil Recipes";
    }
}

