package com.apeman1024.Ichange;

import java.util.List;

import com.apeman1024.entity.Indent;

public interface IMineChange {
	List<Indent> getIndent(String username);

	boolean delIndent(String username, String number);

	boolean del(String username);
}
