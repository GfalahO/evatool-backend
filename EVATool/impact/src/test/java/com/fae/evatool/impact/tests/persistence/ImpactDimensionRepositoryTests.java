package com.fae.evatool.impact.tests.persistence;

import com.fae.evatool.impact.persistence.ImpactDimensionRepository;
import com.fae.evatool.impact.persistence.ImpactDimension;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ImpactDimensionRepositoryTests {
    @Autowired
    private ImpactDimensionRepository impactDimensionRepository;

    @Test
    public void whenFindById_ReturnDimension() {
        // given
        ImpactDimension dimension = getDimension();
        impactDimensionRepository.save(dimension);

        // when
        ImpactDimension found = impactDimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        Assert.assertEquals(dimension.getId(), found.getId());
    }

    @Test
    public void whenFindByName_ReturnDimension() {
        // given
        ImpactDimension dimension = getDimension();
        impactDimensionRepository.save(dimension);

        // when
        ImpactDimension found = impactDimensionRepository.findByName(dimension.getName()).orElse(null);

        // then
        Assert.assertEquals(dimension.getName(), found.getName());
    }

    @Test
    public void whenChangeName_ReturnChangedDimension() {
        // given
        ImpactDimension dimension = getDimension();
        impactDimensionRepository.save(dimension);
        String oldName = dimension.getName();
        String newName = "unsafety";

        // when
        dimension.setName(newName);
        impactDimensionRepository.save(dimension);
        ImpactDimension changedDimension = impactDimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        Assert.assertEquals(newName, changedDimension.getName());
    }

    @Test
    public void whenDelete_ReturnNull(){
        // given
        ImpactDimension dimension = getDimension();
        impactDimensionRepository.save(dimension);

        // when
        impactDimensionRepository.delete(dimension);
        ImpactDimension found = impactDimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        Assert.assertEquals(null, found);
    }

    private ImpactDimension getDimension() {
        return new ImpactDimension("safety", "...");
    }
}
