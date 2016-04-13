package biz.neustar.model;

/**
 * @author Madhukar Reddy
 *
 */
public class Category {

    private String categorySubCategory;
    private String category;
    private String subCategory;

    public Category(String categorySubCategory, String category, String subCategory) {
        this.categorySubCategory = categorySubCategory;
        this.category = category;
        this.subCategory = subCategory;
    }

    public String getCategorySubCategory() {
        return categorySubCategory;
    }

    public void setCategorySubCategory(String categorySubCategory) {
        this.categorySubCategory = categorySubCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

}
