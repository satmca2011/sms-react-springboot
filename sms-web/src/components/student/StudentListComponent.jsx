import React, {useState, useEffect } from 'react'
import { deleteStudent, getAllStudents } from '../../service/StudentService';
import { useNavigate } from 'react-router-dom';


const StudentListComponent = () => {
    const navigator = useNavigate();
    const [students, setStudents] = useState([]);

    useEffect(()=>{
        fetchAllStudents();
    },[])

    function fetchAllStudents(){
        getAllStudents()
        .then((response)=>{
            setStudents(response.data);
        })
        .catch(error => {
            console.error(error);
        })
        
    }

    function handleStudentCreate(id){
        navigator('/add-student')
    }

    function handleStudentEdit(id){
        navigator(`/edit-student/${id}`)
    }

    function handleStudentDelete(id){
       deleteStudent(id)
       .then((response)=>{
         fetchAllStudents();
       })
       .catch((error)=>{console.log(error)})
    }

    return (
        <div className='container'>
            <h2 className='text-center'>Student List</h2>
            <button className='btn btn-primary mb-2' onClick={handleStudentCreate}>Add Student</button>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th scope='col'>#</th>
                        <th scope='col'>First Name</th>
                        <th scope='col'>Last Name</th>
                        <th scope='col'>Email</th>
                        <th scope='col'>Mobile</th>
                        <th scope='col'>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        students.map(student =>
                            <tr scope='row' key={student.id}>
                                <td>{student.id}</td>
                                <td>{student.firstName}</td>
                                <td>{student.lastName}</td>
                                <td>{student.email}</td>
                                <td>{student.mobileNumber}</td>
                                <td>
                                    <button className='btn btn-info' onClick={()=>handleStudentEdit(student.id)}>Edit</button>
                                    <button className='btn btn-danger' onClick={()=>handleStudentDelete(student.id)} style={{marginLeft:'10px'}}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default StudentListComponent