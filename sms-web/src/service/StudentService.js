import axios from "axios";

const BASE_URL = 'http://localhost:8080/api/students';
const ALL_STUDENTS = '/all';
const CREATE = '/create'

export const getAllStudents = () => axios.get(BASE_URL+ALL_STUDENTS);
export const createStudent = (student) => axios.post(BASE_URL+CREATE, student);
export const getStudent = (id) => axios.get(BASE_URL+'/'+id)
export const updateStudent = (id, student) => axios.put(BASE_URL+'/'+id, student);
export const deleteStudent = (id) => axios.delete(BASE_URL+'/'+id);