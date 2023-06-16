package com.example.tanfeeth.Test;

import com.example.tanfeeth.ApiException.ApiException;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Model.Staff;
import com.example.tanfeeth.Repository.OperationCompanyRepository;
import com.example.tanfeeth.Repository.StaffRepository;
import com.example.tanfeeth.Service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StaffServiceTest {

    @InjectMocks
    private StaffService staffService;
    @Mock
    private StaffRepository staffRepository;
    @Mock
    private OperationCompanyRepository operationCompanyRepository;

   void MyUser(int id, String name, String password, String role) {































       @ExtendWith(MockitoExtension.class)
       public class StaffServiceTest {

           @Mock
           private MyUserRepository myUserRepository;
           @Mock
           private StaffRepository staffRepository;
           @InjectMocks
           private StaffService staffService;

get


               @Mock
               private OperationCompanyRepository operationCompanyRepository;
               @Mock
               private StaffRepository staffRepository;
               @InjectMocks
               private StaffService staffService;

               @Test
               public void testGetAllStaffForCompany() {

                   Integer idOC = 1;
                   OperationCompany operationCompany = new OperationCompany(idOC, "Company Name", "Company Address", "Company Phone");
                   Staff staff1 = new Staff(1, "Staff 1", 100.0, "FREE", null, null, operationCompany);
                   Staff staff2 = new Staff(2, "Staff 2", 150.0, "INPROGRESS", null, null, operationCompany);
                   List<Staff> staffList = Arrays.asList(staff1, staff2);
                   when(operationCompanyRepository.findOperationCompanyById(idOC)).thenReturn(operationCompany);
                   when(staffRepository.findStaffByOperationCompany(operationCompany)).thenReturn(staffList);


                   List<Staff> result = staffService.getAllStaffForCompany(idOC);


                   assertEquals(2, result.size());
                   assertTrue(result.contains(staff1));
                   assertTrue(result.contains(staff2));


                   verify(operationCompanyRepository, times(1)).findOperationCompanyById(idOC);
                   verify(staffRepository, times(1)).findStaffByOperationCompany(operationCompany);
                   verifyNoMoreInteractions(operationCompanyRepository, staffRepository);
               }

               @Test
               public void testGetAllStaffForCompanyWithExpiredStaff() {

                   Integer idOC = 1;
                   OperationCompany operationCompany = new OperationCompany(idOC, "Company Name", "Company Address", "Company Phone");
                   Staff staff1 = new Staff(1, "Staff 1", 100.0, "EXPIRED", null, null, operationCompany);
                   Staff staff2 = new Staff(2, "Staff 2", 150.0, "INPROGRESS", null, null, operationCompany);
                   List<Staff> staffList = Arrays.asList(staff1, staff2);
                   when(operationCompanyRepository.findOperationCompanyById(idOC)).thenReturn(operationCompany);
                   when(staffRepository.findStaffByOperationCompany(operationCompany)).thenReturn(staffList);


                   List<Staff> result = staffService.getAllStaffForCompany(idOC);


                   assertEquals(1, result.size());
                   assertTrue(result.contains(staff2));
                   assertFalse(result.contains(staff1));


                   verify(operationCompanyRepository, times(1)).findOperationCompanyById(idOC);
                   verify(staffRepository, times(1)).findStaffByOperationCompany(operationCompany);
                   verify(staffRepository, times(1)).save(staff1);
                   verifyNoMoreInteractions(operationCompanyRepository, staffRepository);
               }
           }



           @Mock
           private StaffRepository staffRepository;
           @Mock
           private MyUserRepository myUserRepository;
           @InjectMocks
           private StaffService staffService;

           @Test
           public void testUpdateStaffWithInvalidStaffId() {

               Integer idOC = 1;
               Integer idStaff = 2;
               Staff newStaff = new Staff();
               MyUser user = new MyUser(idOC, "user1", "password1");
               Staff staff = null;
               when(myUserRepository.findMyUsersById(idOC)).thenReturn(user);
               when(staffRepository.findStaffById(idStaff)).thenReturn(staff);


               ApiException exception = assertThrows(ApiException.class, () -> {
                   staffService.updateStaff(idOC, idStaff, newStaff);
               });
               assertEquals("Staff with this ID dosen't exist!", exception.getMessage());


               verify(myUserRepository, times(1)).findMyUsersById(idOC);
               verify(staffRepository, times(1)).findStaffById(idStaff);
               verifyNoMoreInteractions(myUserRepository, staffRepository);
           }

           @Test
           public void testUpdateStaffWithInvalidOperationCompany() {

               Integer idOC = 1;
               Integer idStaff = 2;
               Staff newStaff = new Staff();
               MyUser user = new MyUser(2, "user1", "password1");
               Staff staff = new Staff(idStaff, "Staff Name", 100.0, "FREE", null, null, user);
               when(myUserRepository.findMyUsersById(idOC)).thenReturn(user);
               when(staffRepository.findStaffById(idStaff)).thenReturn(staff);


               ApiException exception = assertThrows(ApiException.class, () -> {
                   staffService.updateStaff(idOC, idStaff, newStaff);
               });
               assertEquals("Staff with this ID dosen't exist!", exception.getMessage());


               verify(myUserRepository, times(1)).findMyUsersById(idOC);
               verify(staffRepository, times(1)).findStaffById(idStaff);
               verifyNoMoreInteractions(myUserRepository, staffRepository);
           }

           @Test
           public void testUpdateStaff() {

               Integer idOC = 1;
               Integer idStaff = 2;
               Staff newStaff = new Staff(idStaff, "New Name", 200.0, "INPROGRESS", null, null, null);
               MyUser user = new MyUser(idOC, "user1", "password1");
               Staff staff = new Staff(idStaff, "Staff Name", 100.0, "FREE", null, null, user);
               when(myUserRepository.findMyUsersById(idOC)).thenReturn(user);
               when(staffRepository.findStaffById(idStaff)).thenReturn(staff);


               staffService.updateStaff(idOC, idStaff, newStaff);


               assertEquals("New Name", staff.getName());
               assertEquals(200.0, staff.getSalary());
               assertEquals("INPROGRESS", staff.getStatus());
               verify(myUserRepository, times(1)).findMyUsersById(idOC);
               verify(staffRepository, times(1)).findStaffById(idStaff);
               verify(staffRepository, times(1)).save(staff);
               verifyNoMoreInteractions(myUserRepository, staffRepository);
           }
       }
       @ExtendWith(MockitoExtension.class)
       public class StaffServiceTest {

           @Mock
           private StaffRepository staffRepository;
           @Mock
           private OperationCompanyRepository operationCompanyRepository;
           @InjectMocks
           private StaffService staffService;

           @Test
           public void testGetAllStaffExpired() {

               OperationCompany operationCompany = new OperationCompany(1, "company name", "address");
               List<Staff> staffList = new ArrayList<>();
               staffList.add(new Staff(1, "Staff 1", 100.0, "EXPIRED IDENTITY", null, null, operationCompany));
               staffList.add(new Staff(2, "Staff 2", 150.0, "INPROGRESS", null, null, operationCompany));
               staffList.add(new Staff(3, "Staff 3", 200.0, "EXPIRED IDENTITY", null, null, operationCompany));
               when(operationCompanyRepository.findOperationCompanyById(1)).thenReturn(operationCompany);
               when(staffRepository.findStaffByOperationCompany(operationCompany)).thenReturn(staffList);


               List<Staff> expiredStaff = staffService.getAllStaffExpired(1);


               assertThat(expiredStaff).hasSize(2);
               assertThat(expiredStaff.get(0).getId()).isEqualTo(1);
               assertThat(expiredStaff.get(1).getId()).isEqualTo(3);


               verify(operationCompanyRepository, times(1)).findOperationCompanyById(1);
               verify(staffRepository, times(1)).findStaffByOperationCompany(operationCompany);
           }
       }










//
//           MyUser operationCompany = new MyUser(1, "OC", "123", "OC");
//           Project project = new Project(1, "Project 1", operationCompany);
//           List<Integer> staffIds = Arrays.asList(1, 2, 3);

//           when(myUserRepository.findMyUsersById(1)).thenReturn(operationCompany);
//           when(projectRepository.findProjectById(1)).thenReturn(project);
//           when(staffRepository.findStaffById(1)).thenReturn(staff1);
//           when(staffRepository.findStaffById(2)).thenReturn(staff2);
//           when(staffRepository.findStaffById(3)).thenReturn(staff3);
//           when(staffRepository.save(any())).thenReturn(any());
//
//           staffService.assignStaffToProject(1, 1, staffIds);
//
//           Assertions.assertEquals("WORKING", staff1.getStatus());
//           Assertions.assertEquals("WORKING", staff2.getStatus());
//           Assertions.assertEquals("WORKING", staff3.getStatus());
//           Assertions.assertEquals(project, staff1.getProject());
//           Assertions.assertEquals(project, staff2.getProject());
//           Assertions.assertEquals(project, staff3.getProject());
//           Assertions.assertTrue(project.getStaffs().contains(staff1));
//           Assertions.assertTrue(project.getStaffs().contains(staff2));
//           Assertions.assertTrue(project.getStaffs().contains(staff3));
//
//           verify(myUserRepository, times(1)).findMyUsersById(1);
//           verify(projectRepository, times(1)).findProjectById(1);
//           verify(staffRepository, times(3)).findStaffById(any());
//           verify(staffRepository, times(3)).save(any());
//       }
//    public void assignStaffToProject(Integer idOC, Integer projectId, List<Integer> staffIds) {
//        MyUser operationCompany = myUserRepositroy.findMyUsersById(idOC);
//        Project project = projectRepository.findProjectById(projectId);
//        if (project == null)
//            throw new ApiException("Project with this ID dosen't exist!");
//
//        for (int i = 0; i < staffIds.size(); i++) {
//            Staff staff = staffRepository.findStaffById(staffIds.get(i));
//            if (staff == null || staff.getOperationCompany().getId() != operationCompany.getId()) {
//                throw new ApiException("Staff with this ID dosen't exist!");
//            }
//
//            staff.setProject(project);
//            staff.setStatus("WORKING");
//            project.getStaffs().add(staff);
//            staffRepository.save(staff);
//        }
//
//    }
//



}
//
//    Staff staff, staff1, staff2, staff3;
//    MyUser user;
//
//    List<Staff> staffList;
//
//    @BeforeEach
//    public MyUser(int id, String name, String password, String role) {
//        // some code
//    }
//
//
//    user = new MyUser(1, "Nawaf", "123", "ADMIN", null);
//        staff = new Staff(null, "Orange", 10.0, null);
//
//        staff1 = new Staff(null, 1, 10.0, "NEW", new Staff(), user, null);
//        staff2 = new Staff(null, 2, 20.0, "NEW", new Date(), user, null);
//        staff3 = new Staff(null, 3, 30.0, "INPROGRESS", new Date(), user, null);
//
//        staffList = new ArrayList<>();
//        staffList.add(staff1);
//        staffList.add(staff2);
//        staffList.add(staff3);
//
//    }
//
//    @Test
//    public void getAllStaff() {
//        when(staffRepository.findAll()).thenReturn(staffList);
//
//        List<Staff> returnedStaffList = staffService.getAllStaffForAllCompany();
//
//        Assertions.assertEquals(returnedStaffList, staffList);
//        Assertions.assertEquals(3, staffList.size());
//
//        verify(staffRepository, times(1)).findAll();
//    }
//}
//}
//
//        @Test
//        public void deleteOrder(){
//            when(orderRepository.findOrdersById(order1.getId())).thenReturn(order1);
//            orderService.deleteOrder(user.getId(), order1.getId());
//            verify(orderRepository,times(1)).findOrdersById(order1.getId());
//            verify(orderRepository,times(1)).delete(order1);
//        }
//
//        @Test
//        public void changeStatus(){
//            when(orderRepository.findOrdersById(order1.getId())).thenReturn(order1);
//            orderService.changeStatus("COMPLETE",order1.getId());
//            verify(orderRepository,times(1)).findOrdersById(order1.getId());
//            verify(orderRepository, times(1)).save(order1);
//        }
//
//    }
//    }

//        @Autowired
//        private StaffService staffService;
//
//        @Autowired
//        private StaffRepository staffRepository;
//
//        @Autowired
//        private OperationCompanyRepository operationCompanyRepository;
//
//        @Autowired
//        private ProjectRepository projectRepository;
//
//        @Autowired
//        private MyUserRepositroy myUserRepositroy;
//
//        @Test
//        public void testGetAllStaffForAllCompany() {
//            List<Staff> staffList = staffService.getAllStaffForCompany();
//            assert (staffList).isEmpty();
//        }
//
//        @Test
//        public void testGetAllStaffForCompany() {
//            OperationCompany operationCompany = new OperationCompany();
//            operationCompany.setName("Test Company");
//            operationCompanyRepository.save(operationCompany);
//            Staff staff = new Staff();
//            staff.setOperationCompany(operationCompany);
//            staffRepository.save(staff);
//
//            List<Staff> staffList = staffService.getAllStaffForCompany(operationCompany.getId());
//            assert (staffList).isEmpty();
//            assert (staffList).contains(staff);
//        }
//
//        @Test
//        public void testAddStaff() {
//            OperationCompany operationCompany = new OperationCompany();
//            operationCompany.setName("Test Company");
//            operationCompanyRepository.save(operationCompany);
//            Staff staff = new Staff();
//            staff.setName("Test Staff");
//            staff.setOperationCompany(operationCompany);
//
//            staffService.addStaff(operationCompany.getId(), staff);
//
//            List<Staff> staffList = staffRepository.findStaffByOperationCompany(operationCompany);
//            assert (staffList).isEmpty();
//            assert (staffList).contains(staff);
//        }
//
//}