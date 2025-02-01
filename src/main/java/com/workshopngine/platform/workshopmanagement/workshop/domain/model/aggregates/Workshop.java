package com.workshopngine.platform.workshopmanagement.workshop.domain.model.aggregates;

import com.workshopngine.platform.workshopmanagement.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.workshopngine.platform.workshopmanagement.workshop.domain.model.commands.CreateWorkshopCommand;
import com.workshopngine.platform.workshopmanagement.workshop.domain.model.commands.UpdateWorkshopCommand;
import com.workshopngine.platform.workshopmanagement.workshop.domain.model.valueobjects.*;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Workshop extends AuditableAbstractAggregateRoot<Workshop> {
    @Embedded
    private WorkshopInfo information;

    @Embedded
    private Location location;

    @Embedded
    private WorkingSchedule workingSchedule;

    @Embedded
    private Capacity capacity;

    @Embedded
    private OwnerId ownerId;

    private WorkshopStatus status;

    public Workshop() {
        super();
        this.status = WorkshopStatus.CLOSED;
        this.workingSchedule = new WorkingSchedule();
    }

    public Workshop(CreateWorkshopCommand command){
        this();
        this.information = command.information();
        this.location = command.location();
        this.capacity = command.capacity();
        this.ownerId = command.ownerId();
    }

    public void update(UpdateWorkshopCommand command) {
        this.information = command.information();
        this.location = command.location();
        this.capacity = command.capacity();
    }
}
