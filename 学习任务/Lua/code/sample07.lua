
------------ �������뷺�� -----------
--[[

t = { 10, 20, 30 }
for element in values(t)
	print(element)
end

-- values ����һ��������
function values(t)
	local i = 0
	return function() i = i + 1; return t[i] end
end

--]]





function goo(t)
	for k,v in t do
		print(k .. "=" .. v)
	end
end
goo({x=10, y=20})
