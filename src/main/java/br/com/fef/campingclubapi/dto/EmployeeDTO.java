package br.com.fef.campingclubapi.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class EmployeeDTO {

    private Long id_employee;

    @NotNull(message = "Preencha o campo Nome!")
    @NotEmpty(message = "Preencha o campo Nome!")
    private String name;

    private String office;

    private Date date_register;

    @NotNull(message = "Preencha o campo Email!")
    @NotEmpty(message = "Preencha o campo Email!")
    private String cpf;

    @NotNull(message = "Preencha o campo Email!")
    @NotEmpty(message = "Preencha o campo Email!")
    private String rg;

    private Date birth_date;

    private String genre;

    @NotNull(message = "Preencha o campo Email!")
    @NotEmpty(message = "Preencha o campo Email!")
    private String username;

    @NotNull(message = "Preencha o campo Senha!")
    @NotEmpty(message = "Preencha o campo Senha!")
    private String password;


    private CampingDTO camping;

    public EmployeeDTO() {
    }


    public Long getId_employee() {
        return id_employee;
    }

    public void setId_employee(Long id_employee) {
        this.id_employee = id_employee;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }


    public CampingDTO getCamping() {
        return camping;
    }

    public void setCamping(CampingDTO camping) {
        this.camping = camping;
    }



}
