package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.entity.Curso;
import br.com.senac.service.CursoService;

@Controller
@RequestMapping("curso")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;

    @GetMapping("listarCursos") //http:/localhost:8080/curso/listaCursos
    public ModelAndView listarTodosCursos(){
        ModelAndView mv = new ModelAndView("curso/paginaListaCursos");
        mv.addObject("cursos", cursoService.buscarTodosCursos());
        return mv;
    }

    @GetMapping("cadastrarCurso") //http:/localhost:8080/curso/cadastrarCurso
    public ModelAndView cadastrarCurso(){
        ModelAndView mv = new ModelAndView("curso/cadastrarCurso");
        mv.addObject("curso", new Curso());
        return mv;
    }

    @PostMapping("salvar") //http://localhost:8080/curso/salvar
    public ModelAndView salvarCurso(Curso curso){
        cursoService.salvar(curso);
        return listarTodosCursos();
    }

    @GetMapping("excluir/{id}")
    public ModelAndView excluirCurso(@PathVariable("id") Integer id) {
        cursoService.deletar(id);
        return listarTodosCursos();
    }

    @GetMapping("/paginaAlterar/{id}")
    public ModelAndView alterarCurso(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("curso/alterarCurso");
        mv.addObject("curso", cursoService.buscarPorId(id));
        return mv;
    }

    @PostMapping("/salvarAlteracao")
    public ModelAndView alterar(Curso cursoAlterado) {
        cursoService.salvarAlteracao(cursoAlterado);
        return listarTodosCursos();
    }
}
