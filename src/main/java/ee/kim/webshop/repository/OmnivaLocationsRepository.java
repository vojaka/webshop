package ee.kim.webshop.repository;

import ee.kim.webshop.model.entity.OmnivaLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OmnivaLocationsRepository extends JpaRepository<OmnivaLocation,Long> {
}
