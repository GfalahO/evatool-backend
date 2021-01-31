package com.evatool.impact.service.api.rest;

import com.evatool.impact.persistence.entity.Dimension;
import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.service.EntityNotFoundException;

import java.util.List;

public interface DimensionRestService {
    public Dimension getDimensionById(String id) throws EntityNotFoundException;

    public List<Dimension> getAllDimensions();

    public Dimension insertDimension(Dimension dimension); // TODO: Throw entity already exists exception?

    public Dimension updateDimension(Dimension dimension) throws EntityNotFoundException;

    public void deleteDimensionById(String id) throws EntityNotFoundException;
}
