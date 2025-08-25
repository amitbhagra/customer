// AI-Generated Fix: Add Custom Email Validation with Better Error Response
// Generated on: 2025-08-25T12:36:32.158Z

@Service
public class CustomerService {
    
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
    
    public Customer createCustomer(CustomerDto customerDto) {
        // Validate email uniqueness
        if (existsByEmail(customerDto.getEmail())) {
            throw new DuplicateEmailException(
                "Email '" + customerDto.getEmail() + "' is already registered");
        }
        
        // Create customer
        Customer customer = new Customer();
        customer.setEmail(customerDto.getEmail());
        return customerRepository.save(customer);
    }
}