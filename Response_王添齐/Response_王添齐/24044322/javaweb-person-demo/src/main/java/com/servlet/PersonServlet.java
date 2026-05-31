package com.servlet;

import com.dao.PersonDao;
import com.entity.Person;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");

        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setGender(gender);
        person.setPhone(phone);

        PersonDao dao = new PersonDao();
        dao.addPerson(person);

        List<Person> personList = dao.findAll();
        request.setAttribute("personList", personList);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
