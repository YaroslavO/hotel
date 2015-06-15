package com.yaroslav.hotel.search;

import com.yaroslav.hotel.entity.SearchFormEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by employee on 6/15/15.
 */

@RunWith(value = BlockJUnit4ClassRunner.class)
public class SearchFormEntityTest {

    @Test
    public void createNewEmptySearchEntity() throws Exception {
        SearchFormEntity searchEntity = new SearchFormEntity();

        assertNull(searchEntity.getStartDate());
        assertNull(searchEntity.getEndDate());
        assertNull(searchEntity.getSizeSGL());
        assertNull(searchEntity.getSizeDBL());
        assertNull(searchEntity.getBudgetTypeLux());
        assertNull(searchEntity.getBudgetTypeStandard());
        assertNull(searchEntity.getBudgetTypeEconom());
    }

    @Test
    public void createNotEmptySearchEntity() throws Exception {
        SearchFormEntity searchEntity = new SearchFormEntity();
        String startDate = "2015-05-12";
        String endDate = "2015-05-15";

        searchEntity.setStartDate(startDate);
        searchEntity.setEndDate(endDate);
        searchEntity.setSizeDBL(true);
        searchEntity.setSizeSGL(true);
        searchEntity.setBudgetTypeLux(true);
        searchEntity.setBudgetTypeStandard(true);
        searchEntity.setBudgetTypeEconom(true);

        assertThat(searchEntity.getStartDate(), is(startDate));
        assertThat(searchEntity.getEndDate(), is(endDate));
        assertThat(searchEntity.getSizeSGL(), is(true));
        assertThat(searchEntity.getSizeDBL(), is(true));
        assertThat(searchEntity.getBudgetTypeLux(), is(true));
        assertThat(searchEntity.getBudgetTypeStandard(), is(true));
        assertThat(searchEntity.getBudgetTypeEconom(), is(true));
    }

//    @Test
//    public void generateHQLWithNotEmptyDate() throws Exception {
//        SearchFormEntity searchEntity = new SearchFormEntity();
//        String startDate = "2015-05-12";
//        String endDate = "2015-05-15";
//
//        searchEntity.setStartDate(startDate);
//        searchEntity.setEndDate(endDate);
//    }
}
