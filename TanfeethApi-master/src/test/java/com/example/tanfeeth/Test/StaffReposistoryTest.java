package com.example.tanfeeth.Test;


import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Model.Staff;
import com.example.tanfeeth.Repository.StaffRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith( SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    public class StaffRepositoryTest {

        @Autowired
        private StaffRepository staffRepository;

        @Test
        public void testFindStaffById() {
            Staff staff = new Staff();
            staff.setIdStaff(1);
            staffRepository.save(staff);

            Staff foundStaff = staffRepository.findStaffById(1);
            assertThat(foundStaff).isNotNull();
        }

        @Test
        public void testFindStaffByOperationCompany() {
            OperationCompany operationCompany = new OperationCompany();
            operationCompany.setName("Test Company");
            Staff staff = new Staff();
            staff.setOperationCompany(operationCompany);
            staffRepository.save(staff);

            List<Staff> foundStaffList = staffRepository.findStaffByOperationCompany(operationCompany);
            assert (foundStaffList).isNotEmpty();
            assertThat(foundStaffList).contains(staff);
        }

        @Test
        public void testFindStaffByProject() {
            Project project = new Project();
            project.setName("Test Project");
            Staff staff = new Staff();
            staff.setProject(project);
            staffRepository.save(staff);

            List<Staff> foundStaffList = staffRepository.findStaffByProject(project);
            assertThat(foundStaffList).isNotEmpty();
            assertThat(foundStaffList).contains(staff);
        }

        @Test
        public void testFindStaffByStatus() {
            Staff staff = new Staff();
            staff.setStatus("Active");
            staffRepository.save(staff);

            List<Staff> foundStaffList = staffRepository.findStaffByStatus("Active");
            assertThat(foundStaffList).isNotEmpty();
            assertThat(foundStaffList).contains(staff);
        }

    }

