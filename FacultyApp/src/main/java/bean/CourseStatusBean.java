/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Course;
import java.util.Date;
import java.util.Map;
import javax.ejb.Singleton;

/**
 *
 * @author milut
 */
@Singleton
public class CourseStatusBean {
    public Map<Course, Date> coursesAssignMap;

    public void updateAssignmentsMap(Course coursesEntity){
        coursesAssignMap.put(coursesEntity, new Date());
    }
    
}
