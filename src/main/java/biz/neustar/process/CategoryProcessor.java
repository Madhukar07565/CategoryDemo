package biz.neustar.process;

import biz.neustar.model.Category;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author Madhukar Reddy
 *
 */
public class CategoryProcessor {

    private static final String SPACE_SEPERATOR = "\\s+";

    private static final List<String> LEGAL_CATEGORIES = Arrays.asList("PERSON", "PLACE", "ANIMAL", "COMPUTER", "OTHER");

    /**
     * Process the input file and print both categoryCount and categories
     * 
     * @param inputFile - file to be processed
     */
    public void printOutput(File inputFile) {
        List<Category> categories = processFile(inputFile.getAbsolutePath(), LEGAL_CATEGORIES);
        if (CollectionUtils.isNotEmpty(categories)) {
            Map<String, Integer> categoryCountMap = extractCategoryCount(LEGAL_CATEGORIES, categories);
            List<String> categoriesList = extractCategories(categories);
            printCount(categoryCountMap);
            printValues(categoriesList);
        } else {
            System.out.println("Something went wrong while procesing the file");
        }
    }

    /**
     * Iterate over the categoryCountMap and printing to the console
     * 
     * @param categoryCountMap
     *            - categoryMap to print
     */
    private static void printCount(Map<String, Integer> categoryCountMap) {
        System.out.println("Category  Count");
        for (Entry<String, Integer> entry : categoryCountMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /**
     * Iterate over the categories and printing to the console
     * 
     * @param categories
     *            - list of categories to print
     */
    public static void printValues(List<String> categories) {
        System.out.println();
        for (String category : categories) {
            System.out.println(category);
        }
    }

    /**
     * Process the input file to extract the valid list of {@link biz.neustar.model.Category}
     * 
     * @param filePath
     *            - file to be processed
     * @param legalCategories
     *            - categories which are supported
     * @return list of {@link biz.neustar.model.Category} objects
     */
    public List<Category> processFile(String filePath, List<String> legalCategories) {
        List<Category> categoryList = new ArrayList<Category>();
        try (InputStream is = new FileInputStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                boolean isCategoryExists = false;
                if (CollectionUtils.isNotEmpty(categoryList)) {
                    for (Category categoryElement : categoryList) {
                        if (categoryElement.getCategorySubCategory().equalsIgnoreCase(line)) {
                            isCategoryExists = true;
                        }
                    }
                }
                String[] categorySubCategoryPair = line.split(SPACE_SEPERATOR, 2);
                if (categorySubCategoryPair.length == 2) {
                    String category = categorySubCategoryPair[0];
                    String subCategory = categorySubCategoryPair[1];
                    for (String legalCategory : legalCategories) {
                        if (legalCategory.equalsIgnoreCase(category)) {
                            if (!isCategoryExists) {
                                categoryList.add(new Category(line, category, subCategory));
                                break;
                            }
                        }
                    }
                }
            }
            return categoryList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Iterate over the categoryList and extracting categorySubCategory
     * 
     * @param categoryList
     *            - list of category objects
     * @return list of string
     */
    public List<String> extractCategories(List<Category> categoryList) {
        List<String> list = new LinkedList<>();
        for (Category category : categoryList) {
            list.add(category.getCategorySubCategory());
        }
        return list;
    }

    /**
     * Iterate over the categoryList and extracting categoryCountMap
     * 
     * @param legalCategories
     *            - list of category objects
     * @param categoryList
     *            - categories which are supported
     * @return map wit category and count
     */
    public Map<String, Integer> extractCategoryCount(List<String> legalCategories, List<Category> categoryList) {
        Map<String, Integer> categoryCountMap = new LinkedHashMap<String, Integer>();
        for (String legalCategory : legalCategories) {
            int count = 0;
            for (Category updatedCategory : categoryList) {
                if (legalCategory.equalsIgnoreCase(updatedCategory.getCategory())) {
                    categoryCountMap.put(updatedCategory.getCategory(), count += 1);
                } else {
                    categoryCountMap.put(legalCategory, count);
                }
            }
        }
        return categoryCountMap;
    }
}
