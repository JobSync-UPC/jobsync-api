package com.jobsync.jobysncapi.organization.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="recruitment_proccesses", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class RecruitmentProccessses {
}
