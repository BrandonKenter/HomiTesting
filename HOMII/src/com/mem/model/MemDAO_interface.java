package com.mem.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*import com.relationship.model.RelationshipVO;*/

public interface MemDAO_interface {
      public int insert(MemVO memVO);
      public MemVO findByPrimaryKey(Integer mb_no);
      public List<MemVO> getAll();
      public MemVO getOnePic(Integer member_no);
      public void updatePic(MemVO membervo);
      public int updatePwd(MemVO membervo);
      public MemVO login_check(String mb_email, String mb_pwd);
      public int account_activate(String mb_email);
	  public MemVO email_check(String mb_email);
	  public MemVO getPassword(String mb_email);
	  public int updateRandomPws(String mb_email, String mb_pwd);
	  public int updateBalance(MemVO membervo);
	  public MemVO findByPrimaryKeyByMbName(String mb_name);
	/* public Set<RelationshipVO> getRelationshipsByMemberno(Integer member_no); */		  
	/* public List<MemVO> getAll(Map<String, String[]> map); */
}
