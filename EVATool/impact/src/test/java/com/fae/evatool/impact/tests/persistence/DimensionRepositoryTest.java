package com.fae.evatool.impact.tests.persistence;

import com.fae.evatool.impact.persistence.DimensionRepository;
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
public class DimensionRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DimensionRepository dimensionRepository;

    @Test
    public void whenFindByName_thenReturnDimension() {
        // given
        ImpactDimension dimension = getDimension();
        entityManager.persist(dimension);
        //entityManager.flush();

        // when
        ImpactDimension found = dimensionRepository.findByName(dimension.getName());

        // then
        Assert.assertEquals(found.getName(), dimension.getName());
        //Assert.assertThat(found.getName()).isEqualTo(safetyDimension.getName());
    }

    private ImpactDimension getDimension(){
        return new ImpactDimension("safety", "...");
    }
}
