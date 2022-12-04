package servlets;

import dao.CompanyDAO;
import dao.ProductDAO;
import entity.Company;
import entity.Product;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DataManager {
    private final ProductDAO productDAO = new ProductDAO();
    private final CompanyDAO companyDAO = new CompanyDAO();

    public void saveProduct(@NotNull String name, @NotNull String companyName, @NotNull Integer count) {
        Company company;
        try {
            company = companyDAO.getCompanyByName(companyName);
        } catch (IllegalStateException e) {
            companyDAO.saveCompany(new Company(0, companyName));
            company = companyDAO.getCompanyByName(companyName);
        }
        productDAO.saveProduct(new Product(0, name, company.getId(), count));
    }

    public @NotNull List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
