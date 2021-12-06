package ir.imorate.jobify.repository.main;

import ir.imorate.jobify.entity.main.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {

}