package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.CategoryRepository;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author ihber
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category();
        category.setDescription("Category description");
        categoryRepository.save(category).block();

        Vendor vendor = new Vendor();
        vendor.setFirstName("Sam");
        vendor.setLastName("Dummy");
        vendorRepository.save(vendor).block();

        System.out.println("Categories loaded: " + categoryRepository.count().block());
        System.out.println("Vendors loaded: " + vendorRepository.count().block());
    }
}
