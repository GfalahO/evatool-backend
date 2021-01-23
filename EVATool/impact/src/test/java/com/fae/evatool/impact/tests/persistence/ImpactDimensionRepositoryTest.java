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
public class ImpactDimensionRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ImpactDimensionRepository impactDimensionRepository;

    @Test
    public void whenFindByName_thenReturnDimension() {
        // given
        ImpactDimension dimension = getDimension();
        entityManager.persist(dimension);
        //entityManager.flush();

        // when
        ImpactDimension found = impactDimensionRepository.findByName(dimension.getName());

        // then
        Assert.assertEquals(found.getName(), dimension.getName());
        //Assert.assertThat(found.getName()).isEqualTo(safetyDimension.getName());
    }

    private ImpactDimension getDimension(){
        return new ImpactDimension("safety", "...");
    }
}
