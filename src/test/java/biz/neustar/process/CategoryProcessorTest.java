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
    List<String> legalCategories = Arrays.asList("PERSON", "PLACE", "ANIMAL", "COMPUTER", "OTHER");
    File resourcesDirectory = new File("src/test/resources");

    @Test
    public void testProcessValidFile() {

        List<Category> categories = categoryProcessor.processFile(resourcesDirectory + "/ValidCategoryset.txt",
                legalCategories);

        Map<String, Integer> actualMap = categoryProcessor.extractCategoryCount(legalCategories, categories);
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

        List<Category> categories = categoryProcessor.processFile(resourcesDirectory + "/InvalidCategoryset.txt",
                legalCategories);
        categoryProcessor.printOutput(new File(resourcesDirectory+"//InvalidCategoryset.txt"));

        Map<String, Integer> actual = categoryProcessor.extractCategoryCount(legalCategories, categories);
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

        List<Category> categories = categoryProcessor.processFile(resourcesDirectory
                + "/ValidCategorysetWithEmptyLine.txt", legalCategories);
        Map<String, Integer> actual = categoryProcessor.extractCategoryCount(legalCategories, categories);
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

        List<Category> categories = categoryProcessor.processFile(resourcesDirectory
                + "/ValidCategorysetWithoutOneLegalCategory.txt", legalCategories);
        Map<String, Integer> actual = categoryProcessor.extractCategoryCount(legalCategories, categories);
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

        List<Category> categories = categoryProcessor.processFile(resourcesDirectory
                + "/ValidCategorysetWithReverseOrder.txt", legalCategories);

        Map<String, Integer> actualMap = categoryProcessor.extractCategoryCount(legalCategories, categories);
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
