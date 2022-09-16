package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.entity.Aluno;
import br.com.senac.service.AlunoService;

@Controller
@RequestMapping("aluno")
public class AlunoController {
    
    @Autowired
    private AlunoService service;

    @GetMapping("listarAlunos") //http://localhost:8080/aluno/listarAlunos
    public ModelAndView listarTodosAlunos(){
        ModelAndView mv = new ModelAndView("aluno/paginaListaAlunos");
        mv.addObject("alunos", service.buscarTodosAlunos());
        return mv;
    }

    @GetMapping("cadastrarAluno") //http://localhost:8080/aluno/cadastrarAluno
    public ModelAndView cadastrarAluno(){
        ModelAndView mv = new ModelAndView("aluno/cadastrarAluno");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    @PostMapping("salvar") //http://localhost:8080/aluno/salvar
    public ModelAndView salvarAluno(Aluno aluno){
        service.salvar(aluno);
        return listarTodosAlunos();
    }
}
