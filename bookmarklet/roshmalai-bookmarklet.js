const fileInput = document.getElementById('fileInput');
const preview = document.getElementById('preview');
const result = document.getElementById('result');
const closePanel = document.getElementById('closePanel');
const analyzeBtn = document.getElementById('analyzeBtn');

let currentImage = null;

fileInput.addEventListener('change', (event) => {
  const file = event.target.files[0];
  if (!file) return;
  const reader = new FileReader();
  reader.onload = () => {
    preview.src = reader.result;
    preview.style.display = 'block';
    currentImage = reader.result;
  };
  reader.readAsDataURL(file);
});

analyzeBtn.addEventListener('click', () => {
  if (!currentImage) {
    result.textContent = 'কোন ছবি দেওয়া হয়নি। অনুগ্রহ করে একটি চার্টের ছবি আপলোড করুন।';
    return;
  }

  result.textContent = 'দৃশ্যমান চার্টটি সাধারণত ধারাবাহিকভাবে উপরের বা নিচের দিকের দোলন দেখাচ্ছে। এটি একটি শিক্ষা-oriented পর্যবেক্ষণ, পূর্বাভাস নয়।';
});

closePanel.addEventListener('click', () => {
  document.querySelector('.panel').style.display = 'none';
});
