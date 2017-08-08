/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Inga
 */
@Embeddable
public class CoursemodulePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "StudentID")
    private int studentID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TeacherID")
    private int teacherID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CourseID")
    private int courseID;

    public CoursemodulePK() {
    }

    public CoursemodulePK(int studentID, int teacherID, int courseID) {
        this.studentID = studentID;
        this.teacherID = teacherID;
        this.courseID = courseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) studentID;
        hash += (int) teacherID;
        hash += (int) courseID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoursemodulePK)) {
            return false;
        }
        CoursemodulePK other = (CoursemodulePK) object;
        if (this.studentID != other.studentID) {
            return false;
        }
        if (this.teacherID != other.teacherID) {
            return false;
        }
        if (this.courseID != other.courseID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CoursemodulePK[ studentID=" + studentID + ", teacherID=" + teacherID + ", courseID=" + courseID + " ]";
    }
    
}
