/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Inga
 */
@Entity
@Table(name = "studentgrades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Studentgrades.findAll", query = "SELECT s FROM Studentgrades s")
    , @NamedQuery(name = "Studentgrades.findByStudentID", query = "SELECT s FROM Studentgrades s WHERE s.studentgradesPK.studentID = :studentID")
    , @NamedQuery(name = "Studentgrades.findByCourseID", query = "SELECT s FROM Studentgrades s WHERE s.studentgradesPK.courseID = :courseID")
    , @NamedQuery(name = "Studentgrades.findByGradeNumber", query = "SELECT s FROM Studentgrades s WHERE s.gradeNumber = :gradeNumber")})
public class Studentgrades implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentgradesPK studentgradesPK;
    @Column(name = "GradeNumber")
    private Integer gradeNumber;
    @JoinColumn(name = "CourseID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "StudentID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;

    public Studentgrades() {
    }

    public Studentgrades(StudentgradesPK studentgradesPK) {
        this.studentgradesPK = studentgradesPK;
    }

    public Studentgrades(int studentID, int courseID) {
        this.studentgradesPK = new StudentgradesPK(studentID, courseID);
    }

    public StudentgradesPK getStudentgradesPK() {
        return studentgradesPK;
    }

    public void setStudentgradesPK(StudentgradesPK studentgradesPK) {
        this.studentgradesPK = studentgradesPK;
    }

    public Integer getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(Integer gradeNumber) {
        this.gradeNumber = gradeNumber;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentgradesPK != null ? studentgradesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Studentgrades)) {
            return false;
        }
        Studentgrades other = (Studentgrades) object;
        if ((this.studentgradesPK == null && other.studentgradesPK != null) || (this.studentgradesPK != null && !this.studentgradesPK.equals(other.studentgradesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Studentgrades[ studentgradesPK=" + studentgradesPK + " ]";
    }
    
}
