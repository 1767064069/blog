package com.hsp.blog.web.admin;


import com.hsp.blog.po.Tag;
import com.hsp.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)
                                Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));

        return  "admin/tags";

    }
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";

    }
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";

    }
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes redirectAttributes){
        Tag tag1=tagService.getTagByName(tag.getName());
        if(tag1!=null){
            result.rejectValue("name","nameError","不能添加重复标签");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t=tagService.saveTag(tag);
        if(t==null){
            redirectAttributes.addFlashAttribute("message","新增失败");
        }else{
            redirectAttributes.addFlashAttribute("message","新增成功");
        }
        return  "redirect:/admin/tags";
    }

    //修改标签
    @PostMapping("/tags/{id}")
    public String editpost(@Valid Tag tag, @PathVariable Long id, BindingResult result, RedirectAttributes redirectAttributes){
        Tag tag1=tagService.getTagByName(tag.getName());
        if(tag1!=null){
            result.rejectValue("name","nameError","不能添加重复分类");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t=tagService.updateTag(id,tag);
        if(t==null){
            redirectAttributes.addFlashAttribute("message","更新成功");
        }else{
            redirectAttributes.addFlashAttribute("message","更新失败");
        }
        return  "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}