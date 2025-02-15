import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import HeaderComponent from './components/header/HeaderComponent'
import FooterComponent from './components/footer/FooterComponent'
import StudentListComponent from './components/student/StudentListComponent'
import StudentComponent from './components/student/StudentComponent'

function App() {

  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          {/*http://localhost:3000*/}
          <Route path='/' element={<StudentListComponent />} ></Route>
          {/*http://localhost:3000/students*/}
          <Route path='/students' element={<StudentListComponent />} ></Route>
          {/*http://localhost:3000/students/add-student*/}
          <Route path='/add-student' element={<StudentComponent />} ></Route>
          {/*http://localhost:3000/students/edit-student/1*/}
          <Route path='/edit-student/:id' element={<StudentComponent />} ></Route>

        </Routes>

        <FooterComponent />
      </BrowserRouter>
    </>
  )
}

export default App
