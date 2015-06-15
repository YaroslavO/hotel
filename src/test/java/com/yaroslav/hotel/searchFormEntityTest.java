package com.yaroslav.hotel;

import com.yaroslav.hotel.entity.SearchFormEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by employee on 6/15/15.
 */

@RunWith(value = BlockJUnit4ClassRunner.class)
public class searchFormEntityTest {

    @Test
    public void createNewEmptySearchEntity() throws Exception {
        SearchFormEntity searchEntity = new SearchFormEntity();

        assertThat(searchEntity.getStartDate(), is(""));
        assertThat(searchEntity.getEndDate(), is(""));
        assertThat(searchEntity.getSizeSGL(), is(false));
        assertThat(searchEntity.getSizeDBL(), is(false));
        assertThat(searchEntity.getBudgetTypeLux(), is(false));
        assertThat(searchEntity.getBudgetTypeStandard(), is(false));
        assertThat(searchEntity.getBudgetTypeEconom(), is(false));
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

//    @Test(expected = ParseException.class)
//    public void generateHQLBuilderWithEmptyDateStringSearchEntity() throws Exception {
//        SearchFormEntity searchFormEntity = new SearchFormEntity();
//        searchFormEntity.createHQLBuilderForThisFormEntity();
//    }


}
