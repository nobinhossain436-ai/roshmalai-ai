javascript:(function(){
  const panel = document.createElement('div');
  panel.style.position = 'fixed';
  panel.style.right = '12px';
  panel.style.bottom = '12px';
  panel.style.width = '320px';
  panel.style.maxWidth = '90vw';
  panel.style.background = '#111827';
  panel.style.color = '#fff';
  panel.style.borderRadius = '12px';
  panel.style.padding = '16px';
  panel.style.zIndex = '2147483647';
  panel.style.boxShadow = '0 20px 40px rgba(0,0,0,0.35)';
  panel.innerHTML = `
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:12px;">
      <strong>Roshmalai AI</strong>
      <button id="closeRoshmalai" style="padding:6px 10px;border:none;border-radius:8px;cursor:pointer;">x</button>
    </div>
    <p style="margin:0 0 10px;line-height:1.5;">এই বুকমার্কলেট কোনো অ্যাপের স্ক্রিন সরাসরি দেখতে পারে না। এটি কেবল শিক্ষামূলক ব্যাখ্যা ও স্থানীয় ডেটা প্রক্রিয়াকরণে সাহায্য করে।</p>
    <p style="margin:0;line-height:1.5;">কোনো ছবি বা চার্ট না দিলে বিশ্লেষণ করা হবে না।</p>
  `;
  document.body.appendChild(panel);
  document.getElementById('closeRoshmalai').addEventListener('click', function(){ panel.remove(); });
})();
