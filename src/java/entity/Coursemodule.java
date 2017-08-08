/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Inga
 */
@Entity
@Table(name = "coursemodule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coursemodule.findAll", query = "SELECT c FROM Coursemodule c")
    , @NamedQuery(name = "Coursemodule.findByStudentID", query = "SELECT c FROM Coursemodule c WHERE c.coursemodulePK.studentID = :studentID")
    , @NamedQuery(name = "Coursemodule.findByTeacherID", query = "SELECT c FROM Coursemodule c WHERE c.coursemodulePK.teacherID = :teacherID")
    , @NamedQuery(name = "Coursemodule.findByCourseID", query = "SELECT c FROM Coursemodule c WHERE c.coursemodulePK.courseID = :courseID")
    , @NamedQuery(name = "Coursemodule.findBySchedule", query = "SELECT c FROM Coursemodule c WHERE c.schedule = :schedule")})
public class Coursemodule implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CoursemodulePK coursemodulePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Schedule")
    private String schedule;
    @JoinColumn(name = "CourseID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "StudentID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "TeacherID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Teacher teacher;

    public Coursemodule() {
    }

    public Coursemodule(CoursemodulePK coursemodulePK) {
        this.coursemodulePK = coursemodulePK;
    }

    public Coursemodule(CoursemodulePK coursemodulePK, String schedule) {
        this.coursemodulePK = coursemodulePK;
        this.schedule = schedule;
    }

    public Coursemodule(int studentID, int teacherID, int courseID) {
        this.coursemodulePK = new CoursemodulePK(studentID, teacherID, courseID);
    }

    public CoursemodulePK getCoursemodulePK() {
        return coursemodulePK;
    }

    public void setCoursemodulePK(CoursemodulePK coursemodulePK) {
        this.coursemodulePK = coursemodulePK;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coursemodulePK != null ? coursemodulePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coursemodule)) {
            return false;
        }
        Coursemodule other = (Coursemodule) object;
        if ((this.coursemodulePK == null && other.coursemodulePK != null) || (this.coursemodulePK != null && !this.coursemodulePK.equals(other.coursemodulePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Coursemodule[ coursemodulePK=" + coursemodulePK + " ]";
    }
    
}
