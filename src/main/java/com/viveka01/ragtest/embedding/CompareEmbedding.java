package com.viveka01.ragtest.embedding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CompareEmbedding {
    private final HashMap<String, double[]> embeddingMap;

    public CompareEmbedding(HashMap<String, double[]> embeddingMap) {
        this.embeddingMap = embeddingMap;
    }

    public ArrayList<SearchResult> compare(double[] vector) {
        ArrayList<SearchResult> output = new ArrayList<>();

        for (Map.Entry<String, double[]> entry : embeddingMap.entrySet()) {
            double similarity = CosineSimilarity.cosine(vector, entry.getValue());
            output.add(new SearchResult(similarity, entry.getKey()));
        }

        sortResults(output);

        return output;
    }

    private void sortResults(ArrayList<SearchResult> results) {
        results.sort(
                Comparator.comparingDouble(SearchResult::similarity)
                        .reversed()
        );
    }
}