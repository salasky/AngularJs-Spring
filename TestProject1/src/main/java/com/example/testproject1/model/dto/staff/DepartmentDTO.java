package com.example.testproject1.model.dto.staff;

import com.example.testproject1.model.staff.Organization;

import java.util.UUID;

/**
 * DTO класс для сущности {@link com.example.testproject1.model.staff.Department}
 *
 * @author smigranov
 */
public class DepartmentDTO {
    public DepartmentDTO() {
    }

    /**
     * UUID департамента
     */
    private UUID id;
    /**
     * Полное название департамента
     */
    private String fullName;
    /**
     * Короткое название департамента
     */
    private String shortName;
    /**
     * Руководитель департамента
     */
    private String supervisor;
    /**
     * Контактный телефон департамента
     */
    private String contactNumber;
    /**
     * Организация подразделения
     */
    private UUID organizationId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
    }

    public static DepartmentDTO.DepartmentDtoBuilder newBuilder() {
        return new DepartmentDTO().new DepartmentDtoBuilder();
    }

    /**
     * Внутренний класс Builder
     *
     * @author smigranov
     */
    public class DepartmentDtoBuilder {

        public DepartmentDTO.DepartmentDtoBuilder setId(UUID uuid) {
            DepartmentDTO.this.id = uuid;
            return this;
        }

        public DepartmentDTO.DepartmentDtoBuilder setFullName(String fullName) {
            DepartmentDTO.this.fullName = fullName;
            return this;
        }

        public DepartmentDTO.DepartmentDtoBuilder setShortName(String shortName) {
            DepartmentDTO.this.shortName = shortName;
            return this;
        }

        public DepartmentDTO.DepartmentDtoBuilder setSupervisor(String supervisor) {
            DepartmentDTO.this.supervisor = supervisor;
            return this;
        }

        public DepartmentDTO.DepartmentDtoBuilder setContactNumber(String contactNumber) {
            DepartmentDTO.this.contactNumber = contactNumber;
            return this;
        }

        public DepartmentDTO.DepartmentDtoBuilder setOrganization(Organization organization) {
            DepartmentDTO.this.organizationId = organization.getId();
            return this;
        }

        /**
         * Метод build
         *
         * @return Возвращает объект класса {@link DepartmentDTO}
         */
        public DepartmentDTO build() {
            return DepartmentDTO.this;
        }
    }
}
