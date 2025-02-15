import React from 'react'
import { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { createStudent, getStudent, updateStudent } from '../../service/StudentService'

const StudentComponent = () => {
    const { id } = useParams()
    const navigator = useNavigate()
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')
    const [mobileNumber, setMobileNumber] = useState('')
    const [errors, setErrors] = useState({
        firstName: '',
        lastName: '',
        email: '',
        mobileNumber: ''
    })

    useEffect(() => {
        if (id) {
            getStudentById(id);
        }
    }, [id])

    function validateForm() {
        let valid = true;
        const errorsCopy = { ...errors };
        if (firstName.trim()) {
            errorsCopy.firstName = '';
        }
        else {
            errorsCopy.firstName = 'First name is required';
            valid = false;
        }

        if (lastName.trim()) {
            errorsCopy.lastName = '';
        }
        else {
            errorsCopy.lastName = 'Last name is required';
            valid = false;
        }

        if(email.trim()){
            errorsCopy.email='';
        }
        else{
            errorsCopy.email='Email is required';
            valid = false;
        }
        if(mobileNumber.trim()){
            errorsCopy.mobileNumber='';
        }
        else{
            errorsCopy.mobileNumber='Mobile Number is required';
            valid=false;
        }
        setErrors(errorsCopy);
        return valid;
    }

    function saveModifyStudent(e) {
        e.preventDefault();
        const student = { firstName, lastName, email, mobileNumber }
        console.log(student);
        if (validateForm()) {
            if (id) {
                modifyStudent(id, student);
            }
            else {
                saveStudent(student);
            }
        }
    }

    function saveStudent(student) {
        createStudent(student)
            .then((response) => {
                console.log(response.data)
                navigator('/students')
            })
            .catch(error => {
                console.error(error)
            })
    }

    function modifyStudent(id, student) {
        updateStudent(id, student)
            .then((response) => {
                console.log(response.data)
                navigator('/students')
            })
            .catch(error => {
                console.error(error)
            })
    }

    function getStudentById(id) {
        getStudent(id)
            .then((response) => {
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setEmail(response.data.email);
                setMobileNumber(response.data.mobileNumber);
            })
            .catch((error) => {
                console.error(error);
            })
    }

    function pageTitle(){
        if(id){
            return <h2 className='text-center'>Edit Student</h2>
        }
        else{
            return <h2 className='text-center'>Add Student</h2>
        }
    }

    return (
        <div className='container'>
            <br></br><br></br>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    {pageTitle()}
                    <div className='card-body'>
                        {/*First Name Input Control*/}
                        <div className='form-group mb-2'>
                            <label className="form-label">First Name:</label>
                            <input
                                type='text'
                                name='firstName'
                                placeholder='Enter First Name'
                                value={firstName}
                                className={`form-control ${errors.firstName ? 'is-invalid':''}`}
                                onChange={(e) => { setFirstName(e.target.value) }}
                            >
                            </input>
                            {errors.firstName && <div className='invalid-feedback'>{errors.firstName}</div>}
                        </div>

                        {/*Last Name Input Control*/}
                        <div className='form-group mb-2'>
                            <label className="form-label">Last Name:</label>
                            <input
                                type='text'
                                name='lastName'
                                placeholder='Enter Last Name'
                                value={lastName}
                                className={`form-control ${errors.lastName ? 'is-invalid' : ''}`}
                                onChange={(e) => { setLastName(e.target.value) }}
                            >
                            </input>
                            {errors.lastName && <div className='invalid-feedback'>{errors.lastName}</div>}
                        </div>

                        {/*Email Input Control*/}
                        <div className='form-group mb-2'>
                            <label className="form-label">Email:</label>
                            <input
                                type='text'
                                name='email'
                                placeholder='Enter Email'
                                value={email}
                                className={`form-control ${errors.email ? 'is-invalid':''}`}
                                onChange={(e) => { setEmail(e.target.value) }}
                            >
                            </input>
                            {errors.email && <div className='invalid-feedback'>{errors.email}</div>}
                        </div>

                        {/*Mobile Input Control*/}
                        <div className='form-group mb-2'>
                            <label className="form-label">Mobile:</label>
                            <input
                                type='text'
                                name='mobileNumber'
                                placeholder='Enter Mobile Number'
                                value={mobileNumber}
                                className={`form-control ${errors.mobileNumber?'is-invalid':''}`}
                                onChange={(e) => { setMobileNumber(e.target.value) }}
                            >
                            </input>
                            {errors.mobileNumber && <div className='invalid-feedback'>{errors.mobileNumber}</div>}
                        </div>
                        <button className='btn btn-success' onClick={saveModifyStudent}>Save</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default StudentComponent