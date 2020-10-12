package guru.springframework.spring5webfluxrest.repositories;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author ihber
 */
public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
