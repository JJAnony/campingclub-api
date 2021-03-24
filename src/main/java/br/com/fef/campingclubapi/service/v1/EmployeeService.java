package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.repository.EmployeeRepository;
import br.com.fef.campingclubapi.service.interfaces.IStandardMessageService;
import br.com.fef.campingclubapi.dto.EmployeeDTO;
import br.com.fef.campingclubapi.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IStandardMessageService<EmployeeDTO> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> list(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public EmployeeDTO get(Long id) throws Exception {
        return null;
    }

    @Override
    public MessageDTO save(EmployeeDTO employeeDTO) throws Exception {
        return null;
    }

    @Override
    public MessageDTO update(Long id, EmployeeDTO employeeDTO) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public void verifyIfExists(Long id) throws Exception {

    }
}
