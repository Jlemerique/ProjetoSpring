package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.entity.Turma;
import br.com.senac.service.TurmaService;

@Controller
@RequestMapping("turma")
public class TurmaController {
    
    @Autowired
    private TurmaService turmaService;

    @GetMapping("listarTurmas")
    public ModelAndView listarTodasTurmas(){
        ModelAndView mv = new ModelAndView("turma/paginaListaTurmas");
        mv.addObject("turmas", turmaService.buscarTodasTurmas());
        return mv;
    }

    @GetMapping("cadastrarTurma") //http:/localhost:8080/turma/cadastrarTurma
    public ModelAndView cadastrarTurma(){
        ModelAndView mv = new ModelAndView("turma/cadastrarTurma");
        mv.addObject("turma", new Turma());
        return mv;
    }

    @PostMapping("salvar") //http://localhost:8080/turma/salvar
    public ModelAndView salvarProfessor(Turma turma){
        turmaService.salvar(turma);
        return listarTodasTurmas();
    }
}
