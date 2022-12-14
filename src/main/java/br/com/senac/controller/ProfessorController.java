package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.entity.Professor;
import br.com.senac.service.ProfessorService;

@Controller
@RequestMapping("professor")
public class ProfessorController {
    
    @Autowired
    private ProfessorService professorService;

    @GetMapping("listarProfessores")
    public ModelAndView listarTodosProfessores(){
        ModelAndView mv = new ModelAndView("professor/paginaListaProfessores");
        mv.addObject("professores", professorService.buscarTodosProfessores());
        return mv;
    }

    @GetMapping("cadastrarProfessor") //http:/localhost:8080/professor/cadastrarProfessor
    public ModelAndView cadastrarProfessor(){
        ModelAndView mv = new ModelAndView("professor/cadastrarProfessor");
        mv.addObject("professor", new Professor());
        return mv;
    }

    @PostMapping("salvar") //http://localhost:8080/professor/salvar
    public ModelAndView salvarProfessor(Professor professor){
        professorService.salvar(professor);
        return listarTodosProfessores();
    }

    @GetMapping("excluir/{id}")
    public ModelAndView excluirProfessor(@PathVariable("id") Integer id) {
        professorService.deletar(id);
        return listarTodosProfessores();
    }

    @GetMapping("/paginaAlterar/{id}")
    public ModelAndView alterarProfessor(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("professor/alterarProfessor");
        mv.addObject("professor", professorService.buscarPorId(id));
        return mv;
    }

    @PostMapping("/salvarAlteracao")
    public ModelAndView alterar(Professor professorAlterado) {
        professorService.salvarAlteracao(professorAlterado);
        return listarTodosProfessores();
    }
}
