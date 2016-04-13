package biz.neustar.process;

import biz.neustar.model.Category;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Madhukar Reddy
 *
 */
public class CategoryProcessorTest {

    CategoryProcessor categoryProcessor = new CategoryProcessor();
    private final static List<String> LEGAL_CATEGORIES = Arrays.asList("PERSON", "PLACE", "ANIMAL", "COMPUTER", "OTHER");
    private final static File RESOURCES_DIRECTORY = new File("src/test/resources");

    @Test
    public void testProcessValidFile() {

        List<Category> categories = categoryProcessor.processFile(RESOURCES_DIRECTORY + "/ValidCategoryset.txt",
                LEGAL_CATEGORIES);

        Map<String, Integer> actualMap = categoryProcessor.extractCategoryCount(LEGAL_CATEGORIES, categories);
        Map<String, Integer> expectedMap = new LinkedHashMap<String, Integer>();
        expectedMap.put("PERSON", 2);
        expectedMap.put("PLACE", 2);
        expectedMap.put("COMPUTER", 1);
        expectedMap.put("OTHER", 1);
        expectedMap.put("ANIMAL", 2);
        Assert.assertEquals(expectedMap, actualMap);

        List<String> actualList = categoryProcessor.extractCategories(categories);
        List<String> expectedList = new LinkedList<String>();
        expectedList.add("PERSON Bob Jones");
        expectedList.add("PLACE Washington");
        expectedList.add("PERSON Mary");
        expectedList.add("COMPUTER Mac");
        expectedList.add("OTHER Tree");
        expectedList.add("ANIMAL Dog");
        expectedList.add("PLACE Texas");
        expectedList.add("ANIMAL Cat");
        Assert.assertEquals(expectedList, actualList);

    }

    @Test
    public void testProcessInvalidCategorySet() {

        List<Category> categories = categoryProcessor.processFile(RESOURCES_DIRECTORY + "/InvalidCategoryset.txt",
                LEGAL_CATEGORIES);
        categoryProcessor.printOutput(new File(RESOURCES_DIRECTORY+"//InvalidCategoryset.txt"));

        Map<String, Integer> actual = categoryProcessor.extractCategoryCount(LEGAL_CATEGORIES, categories);
        Map<String, Integer> expected = new LinkedHashMap<String, Integer>();
        expected.put("PERSON", 1);
        expected.put("PLACE", 2);
        expected.put("ANIMAL", 2);
        expected.put("COMPUTER", 1);
        expected.put("OTHER", 1);

        Assert.assertEquals(expected, actual);

        List<String> actualList = categoryProcessor.extractCategories(categories);
        List<String> expectedList = new LinkedList<String>();
        expectedList.add("PLACE Washington");
        expectedList.add("PERSON Mary");
        expectedList.add("COMPUTER Mac");
        expectedList.add("OTHER Tree");
        expectedList.add("ANIMAL Dog");
        expectedList.add("PLACE Texas");
        expectedList.add("ANIMAL Cat");
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void testProcessFileWithEmptyLines() {

        List<Category> categories = categoryProcessor.processFile(RESOURCES_DIRECTORY
                + "/ValidCategorysetWithEmptyLine.txt", LEGAL_CATEGORIES);
        Map<String, Integer> actual = categoryProcessor.extractCategoryCount(LEGAL_CATEGORIES, categories);
        Map<String, Integer> expected = new LinkedHashMap<String, Integer>();
        expected.put("PERSON", 2);
        expected.put("PLACE", 1);
        expected.put("ANIMAL", 1);
        expected.put("COMPUTER", 1);
        expected.put("OTHER", 1);

        Assert.assertEquals(expected, actual);

        List<String> actualList = categoryProcessor.extractCategories(categories);
        List<String> expectedList = new LinkedList<String>();
        expectedList.add("PERSON Bob Jones");
        expectedList.add("PLACE Washington");
        expectedList.add("PERSON Mary");
        expectedList.add("OTHER Tree");
        expectedList.add("ANIMAL Cat");
        expectedList.add("COMPUTER Mac");

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void testProcessFileWithoutOneLegalCategory() {

        List<Category> categories = categoryProcessor.processFile(RESOURCES_DIRECTORY
                + "/ValidCategorysetWithoutOneLegalCategory.txt", LEGAL_CATEGORIES);
        Map<String, Integer> actual = categoryProcessor.extractCategoryCount(LEGAL_CATEGORIES, categories);
        Map<String, Integer> expected = new LinkedHashMap<String, Integer>();
        expected.put("PERSON", 2);
        expected.put("PLACE", 1);
        expected.put("ANIMAL", 0);
        expected.put("COMPUTER", 1);
        expected.put("OTHER", 1);

        Assert.assertEquals(expected, actual);

        List<String> actualList = categoryProcessor.extractCategories(categories);
        List<String> expectedList = new LinkedList<String>();
        expectedList.add("PERSON Bob Jones");
        expectedList.add("PLACE Washington");
        expectedList.add("PERSON Mary");
        expectedList.add("COMPUTER Mac");
        expectedList.add("OTHER Tree");

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void testProcessValidCategoryWithReverseOrder() {

        List<Category> categories = categoryProcessor.processFile(RESOURCES_DIRECTORY
                + "/ValidCategorysetWithReverseOrder.txt", LEGAL_CATEGORIES);

        Map<String, Integer> actualMap = categoryProcessor.extractCategoryCount(LEGAL_CATEGORIES, categories);
        Map<String, Integer> expectedMap = new LinkedHashMap<String, Integer>();
        expectedMap.put("PERSON", 2);
        expectedMap.put("PLACE", 2);
        expectedMap.put("COMPUTER", 1);
        expectedMap.put("OTHER", 1);
        expectedMap.put("ANIMAL", 2);
        Assert.assertEquals(expectedMap, actualMap);

        List<String> actualList = categoryProcessor.extractCategories(categories);
        List<String> expectedList = new LinkedList<String>();
        expectedList.add("ANIMAL Cat");
        expectedList.add("PLACE Texas");
        expectedList.add("ANIMAL Dog");
        expectedList.add("OTHER Tree");
        expectedList.add("PERSON Bob Jones");
        expectedList.add("COMPUTER Mac");
        expectedList.add("PERSON Mary");
        expectedList.add("PLACE Washington");
        Assert.assertEquals(expectedList, actualList);

    }
    
}
